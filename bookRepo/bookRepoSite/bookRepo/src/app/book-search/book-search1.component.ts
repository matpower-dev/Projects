import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.css']
})
export class BookSearchComponent implements OnInit {

  sayHi(){
    this.httpClient.get('http://127.0.0.1:5002/').subscribe(data => {
       this.serverData = data as JSON;
       console.log(this.serverData)
    })
  }

  constructor() { }

  ngOnInit(): void {
  }

}
