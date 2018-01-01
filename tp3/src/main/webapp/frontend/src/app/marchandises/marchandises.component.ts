import { Component, OnInit } from '@angular/core';
import {MarchandisesService } from '../marchandises.service';
import {Marchandisesmodele} from "../marchandisesmodele";
import {Observable} from "rxjs";
import {ApprovisionnementserviceService} from "../approvisionnementservice.service";
import {Approvisionnement} from "../approvisionnement";
import { LivraisonserviceService} from "../livraisonservice.service"


@Component({
  selector: 'app-marchandises',
  templateUrl: './marchandises.component.html',
  styleUrls: ['./marchandises.component.css'],
  providers: [MarchandisesService, ApprovisionnementserviceService, LivraisonserviceService]
})
export class MarchandisesComponent implements OnInit {
  title = "Liste des marchandises";
  marchandiseList: Marchandisesmodele[] = [];
  marchandiseItem : Marchandisesmodele;
  controle: boolean;
  approvisionnement: Approvisionnement[] =[];
  formModificationParent :Marchandisesmodele;
  selectedIndex = -1;
  erreur = false;
  non_erreur = false;



  constructor(private LivraisonserviceService: LivraisonserviceService,
              private MarchandisesService: MarchandisesService,
              private ApprovisionnementserviceService: ApprovisionnementserviceService) { }

  getkey(obj:Object , key:string): string{
    if(obj === null){
      return "gttgt";
    }
    let stringobject = obj[key].toString()
    return stringobject;
  }
  setSelected(id: number) {


    this.ApprovisionnementserviceService.getApprovisionnement()
      .subscribe(approvisionnement => {
        console.log(approvisionnement);
        this.traitementAppr(approvisionnement,id);
      });
    /*this.LivraisonserviceService.getLivraison()
      .subscribe(livraison => {
        console.log(livraison);
        this
        this.traitementAppr(livraison,id);
      });
*/


  }

  DeleteRow(id : number) {
    this.MarchandisesService.DeleteMarchandise(id).then(
      (res) => {
        this.erreur = false;
        this.non_erreur = true;
        Materialize.toast('La marchandise a été bien supprimé ', 2000, 'green rounded top right');
        this.getMarchandises();

      },
      (err) => {
        this.non_erreur = false;
      }
    )
  }
  traitementAppr(data, id)  {
   // console.log(typeof (data.find(item => item.refMarchandise === id) ) == "undefined" && iditem ==0, iditem == 0);
   if(data.length != 0) {
     if (typeof (data.find(item => item.refMarchandise === id) ) == "undefined") {
       this.DeleteRow(id);

     }
     else {
       Materialize.toast('Vous ne pouvez pas supprimer cette marchandise ', 2000, 'red rounded top right');
     }
   }
   else {
     this.DeleteRow(id);

   }
    }

  SelectedID(id: number): Marchandisesmodele {
   // this.formModificationParent = "id";
    //this.form.controls['dept'].setValue(selected.id);
    this.formModificationParent = this.marchandiseList.find(item => item.reference === id);
    console.log(this.formModificationParent);
    return this.formModificationParent;
  }
  getMarchandises(): void {
    this.MarchandisesService.getMarchandise()
      .subscribe(
        marchandiseList =>this.marchandiseList = marchandiseList,
        error => Materialize.toast('Une erreur est survenur réessayer ', 2000, 'red rounded top right')
      );
  }
  getMarchandisesByID(id): Observable<Marchandisesmodele> {
    return this.MarchandisesService.getMarchandiseByID(id)

  }

  ngOnInit() {

    this.getMarchandises();
    $(document).ready(function() {
      //the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
      $('.modal').modal();

    });

    $('#modal2').modal({
        complete: function() { this.control = false; } // Callback for Modal close
      }
    );

  }

}
