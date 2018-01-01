import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  menuImage = "../assets/menu.jpeg";

  constructor() { }

  ngOnInit() {
  // Initialize collapse button
    //$('.button-collapse').sideNav('show');
    //$(".button-collapse").sideNav({
      //edge: 'left', // Choose the horizontal origin
      //closeOnClick: false, // Closes side-nav on <a> clicks, useful for Angular/Meteor
      //draggable: true // Choose whether you can drag to open on touch screens,
    //});
    // Initialize collapsible (uncomment the line below if you use the dropdown variation)
    //$('.collapsible').collapsible();
  }

}
