import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.css']
})
export class BookSearchComponent implements OnInit {


  private serverData: JSON;
  private employeeData: JSON;
  a = 1;
  sayHi(): void {
      this.httpClient.get('http://127.0.0.1:5002/').subscribe(data => {
      this.serverData = data as JSON;
    })
  }

  getAllEmployees(): void {
      this.httpClient.get('http://127.0.0.1:5002/employees?q=inauthor:keyes+intitle:flowers').subscribe(data => {
      this.employeeData = data as JSON;
      console.log(data)
    })
  }

  constructor(private httpClient: HttpClient) {

  }

  ngOnInit(): void {
      this.getAllEmployees()
  }

}
