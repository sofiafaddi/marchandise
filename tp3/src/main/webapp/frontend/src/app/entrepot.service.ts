import { Injectable } from '@angular/core';
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs";
import { EntrepotModele } from "./entrepotmodele";

@Injectable()
export class EntrepotService {
  BaseURLEntrepot = "http://localhost:8080/entrepot/";

  constructor(private http: Http){ }

  /*getEntrepot(): Observable<Marchandisemodele[]>{
    return this.http.get(this.MarchandiseURL).
    map((response: Response) => {
      return <Marchandisemodele[]>response.json();
    })
      .catch(this.handleError);
  }*/


  addEntrepot(data) {
    return new Promise((resolve, reject) => {
      this.http
        .post(this.BaseURLEntrepot, data)
        .map(res => res.json())
        // This catch is very powerfull, it can catch all errors
        .catch((err: Response) => {
          // The err.statusText is empty if server down (err.type === 3)
          console.log((err.statusText || "Can't join the server."));
          // Really usefull. The app can't catch this in "(err)" closure
          reject((err.statusText || "Can't join the server."));
          // This return is required to compile but unuseable in your app
          return Observable.throw(err);
        })
        // The (err) => {} param on subscribe can't catch server down error so I keep only the catch
        .subscribe(data => { resolve(data) })
    })

  }


  /*getEntrepots(): Observable<EntrepotModele[]>{
    return this.http.get(this.BaseURLEntrepot).
    map((response: Response) => {
      return <EntrepotModele[]>response.json();
    });
      //.catch(this.handleError);
  }*/
  public getEntrepots(): Observable<EntrepotModele[]>{
    return this.http
      .get(this.BaseURLEntrepot)
      .map(response => {
        const ListEntrepot = response.json();
        return ListEntrepot.map((ListEntrepot) =>
          new EntrepotModele(ListEntrepot));
      }).catch(this.handleError); // Attach an error handler to log potential errors to the console:
  }

  private  handleError(error: Response) {
    return Observable.throw(error.statusText);
  }


}
