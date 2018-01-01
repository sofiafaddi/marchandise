import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {Livraison} from "./livraison";

@Injectable()
export class LivraisonserviceService {
  BaseURLLivraison = "http://localhost:8080/livraison/";

  constructor(private http: Http){ }

  public getLivraison(): Observable<Livraison[]>{
    return this.http
      .get(this.BaseURLLivraison)
      .map(response => {
        const LivraisonList = response.json();
        return LivraisonList.map((LivraisonList) =>
          new Livraison(LivraisonList));
      }).catch(this.handleError); // Attach an error handler to log potential errors to the console:
  }
  private  handleError(error: Response) {
    return Observable.throw(error.statusText);
  }


}
