import { Component, OnInit } from '@angular/core';
import {LivraisonserviceService} from "../livraisonservice.service";
import {Livraison} from "../livraison";

@Component({
  selector: 'app-livraison',
  templateUrl: './livraison.component.html',
  styleUrls: ['./livraison.component.css'],
  providers: [LivraisonserviceService]
})
export class LivraisonComponent implements OnInit {
  title="Liste des livraisons";
  LivraisonList: Livraison[];

  constructor(private Livraisonserviceservice: LivraisonserviceService ) { }
getLivraison() {
  this.Livraisonserviceservice
    .getLivraison()
    .subscribe(
      (LivraisonList) => {
        this.LivraisonList = LivraisonList;
      }
    );

}
  public ngOnInit() {
    $(document).ready(function () {
      //the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
      $('.modal').modal();

    });
    this.getLivraison();
  }

}
