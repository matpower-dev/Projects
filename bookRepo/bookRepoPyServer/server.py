from flask import Flask, request
from flask_restful import Resource, Api
from flask_cors import CORS, cross_origin
import requests
import uuid

app = Flask(__name__)
CORS(app)
api = Api(app)

def exists(var):
    return var in globals()

def uniqueid():
    return uuid.uuid4().int & (1<<64)-1

def getPropertyResponse(x, property, extralevel="volumeInfo"):
    if extralevel=="no":
        if not property in x.keys():
             out = ""
        else:
             out = x[property]
        return out

    if not property in x[extralevel].keys():
       return ""
    else:
        out = x["volumeInfo"][property]
        if property == "subtitle":
            out = "(" + out + ")"
        return out

class Employees(Resource):

    def get(self):
        fetchUrl = "https://www.googleapis.com/books/v1/volumes?"
        intitle = request.args.get("intitle")
        inauthor = request.args.get("inauthor")
        q = request.args.get("q")
        if not q==None:
            fetchUrl = fetchUrl + "q="+q
        else:
            return "q is a required parameter to the Google Books api."
        if not inauthor==None:
            fetchUrl = fetchUrl + "inauthor="+inauthor
        if not intitle==None:
             fetchUrl = fetchUrl + "&intitle="+intitle
        googlebooks_response = requests.get(fetchUrl)
        if googlebooks_response.ok:
            bookitems = googlebooks_response.json()["items"]
            titles = [ getPropertyResponse(x, "title") for x in bookitems]
            subtitles = [ getPropertyResponse(x, "subtitle") for x in bookitems]
            description = [ getPropertyResponse(x, "description") for x in bookitems]
            authors = [ getPropertyResponse(x, "authors") for x in bookitems]
            imageLinks = [ getPropertyResponse(x, "imageLinks") for x in bookitems]
            selfLink = [ getPropertyResponse(x, "selfLink", extralevel="no") for x in bookitems]
            selectableMenu = []
            authors = [ (", ".join(i)) for i in authors]
            titleCondensed = [i + " " + j for i, j in zip(titles, subtitles)]
            for i in range(len(titles)):
                res={}
                print( uniqueid)
                res["id"] =  uniqueid()
                res["title"] = titleCondensed[i]
                res["description"] = description[i]
                res["selfLink"] = selfLink[i]
                res["thumbnail"] = imageLinks[i]
                res["authors"] = authors[i]
                selectableMenu.append(res)
            print(selectableMenu)
            #selectableMenu = [i + " " + j for i, j in zip(titles, subtitles)]
            return selectableMenu
        else:
            return googlebooks_response.status_code

api.add_resource(Employees, '/employees') # Route_1

if __name__ == '__main__':
     app.run(port=5002)
