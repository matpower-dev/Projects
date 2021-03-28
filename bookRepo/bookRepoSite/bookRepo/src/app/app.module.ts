import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookSearchComponent } from './book-search/book-search.component';
import { HttpClientModule } from '@angular/common/http';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { FormsModule } from '@angular/forms';
import { FilteredSelectModule } from "filtered-select";
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown';
import { SelectmultiComponent } from './selectmulti/selectmulti.component';

@NgModule({
  declarations: [
    AppComponent,
    BookSearchComponent,
    SearchBarComponent,
    SelectmultiComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    FilteredSelectModule,
    AngularMultiSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
