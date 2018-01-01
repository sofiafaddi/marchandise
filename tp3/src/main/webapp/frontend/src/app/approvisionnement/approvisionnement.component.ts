import { Component, OnInit } from '@angular/core';
import { MarchandisesService } from '../marchandises.service'
import {Marchandisesmodele} from "../marchandisesmodele";
import {EntrepotModele} from "../entrepotmodele";
import {EntrepotService} from "../entrepot.service";
import {Approvisionnement} from "../approvisionnement";
import {ApprovisionnementserviceService} from "../approvisionnementservice.service";

@Component({
  selector: 'app-approvisionnement',
  templateUrl: './approvisionnement.component.html',
  styleUrls: ['./approvisionnement.component.css'],
  providers:[ApprovisionnementserviceService]
})
export class ApprovisionnementComponent implements OnInit {
  title="Liste des approvisionnements";
  marchandiseList : Marchandisesmodele[];
  EntrepotList: EntrepotModele[];
  ApprovisionnementList: Approvisionnement[];
  ApprovisionnementItem: Approvisionnement;

  constructor( private MarchandisesService : MarchandisesService, private EntrepotService: EntrepotService,
               private approvisionnementserviceservice: ApprovisionnementserviceService

  ) { }

  getApprovisionnent(): void {
    this.approvisionnementserviceservice.getApprovisionnement()
      .subscribe(
        (ApprovisionnementList) => {
          this.ApprovisionnementList = ApprovisionnementList;
        });
  }

  SelectedID(id: number): Approvisionnement {
    // this.formModificationParent = "id";
    //this.form.controls['dept'].setValue(selected.id);
    this.ApprovisionnementItem = this.ApprovisionnementList.find(item => item.id === id);
    console.log(this.ApprovisionnementItem);
    return this.ApprovisionnementItem;
  }

  public ngOnInit() {
    $(document).ready(function() {
      //the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
      $('.modal').modal();

    });
    this.getApprovisionnent();
    this.MarchandisesService
      .getMarchandise()
      .subscribe(
        (marchandiseList) => {
          this.marchandiseList = marchandiseList;
        }
      );
    this.EntrepotService
      .getEntrepots()
      .subscribe(
        (EntrepotList) => {
          this.EntrepotList = EntrepotList;
        }
      );


  }

}
