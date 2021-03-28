import { Component, OnInit } from '@angular/core';
declare const window: any;
import { option } from 'filtered-select';


const options: option[] = [
  { text: 'Tim', id: 'TP', group: 'Parents' },
  { text: 'Ben', id: 'BP', group: 'Parents' },
  { text: 'Katie', id: 'KP', group: 'Parents' },
  { text: 'John', id: 'JP', group: 'Grandparents' },
  { text: 'Sue', id: 'SP', group: 'Grandparents' },
  { text: 'Sarah', id: 'SR', group: 'Parents' },
  { text: 'Claire', id: 'CB', group: 'Parents' },
  { text: 'Drew', id: 'AM', group: 'Parents' },
  { text: 'Molly', id: 'MP', group: 'Kids' },
  { text: 'Lucy', id: 'LP', group: 'Kids' },
  { text: 'Jess', id: 'JP2', group: 'Kids' },
  { text: 'George', id: 'GP', group: 'Kids' },
  { text: 'Daisy', id: 'DM', group: 'Kids' },
  { text: 'Benny', id: 'BP', group: 'Nicknames' },
  { text: 'Timbo', id: 'TP', group: 'Nicknames' },
  { text: 'Richo', id: 'SR', group: 'Nicknames' },
];

const db = {
    "03067":["Bake Rolls 100g","snack"],
    "04089":["Potato Chips 70g","snack"],
    "05612":["Ice Coffee 100ml","drink"],
    "07740":["Sparkling Water 1.5l","drink"]
}

@Component({
   selector: 'app-search-bar',
   templateUrl: './search-bar.component.html',
   styleUrls: ['./search-bar.component.css']
 })
export class SearchBarComponent implements OnInit {

  options = options
  searchText =  ""
  resultList: string[]
  isDropdown=0

  searchDB(elem): void {
    console.log(elem)
    let selector = document.getElementById("selector");
    if (elem.trim() !== "") {
        this.isDropdown=1
        selector.innerHTML = "";
        let empty = true;
        this.resultList = []
        for (let item in db) {
            let str = [item.toLowerCase(), db[item][0].toLowerCase(), db[item][1].toLowerCase()].join();
            console.log(str)
            if (str.indexOf(elem) !== -1) {
                this.resultList.push(str);
                empty = false;
            }
        }
        console.log(this.resultList)
    }else{
        this.resultList = []
    }
  }

  focusFunction(): void {
      this.resultList = []
  }

  toString(): void {  // ...
  }

  onResult(x): string {
      console.log((x))
      return x.value
  }
  
  constructor() {
  }

  ngOnInit(): void {
  }

}
