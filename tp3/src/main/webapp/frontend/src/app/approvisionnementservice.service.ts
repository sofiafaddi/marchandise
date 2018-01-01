import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Approvisionnement} from "./approvisionnement";
import {Http} from "@angular/http";

@Injectable()
export class ApprovisionnementserviceService {
  BaseURLAppro = "http://localhost:8080/approvisionnement/";

  constructor(private http: Http){ }

  public getApprovisionnement(): Observable<Approvisionnement[]>{
    return this.http
      .get(this.BaseURLAppro)
      .map(response => {
        const ListApprovisionnement = response.json();
        return ListApprovisionnement.map((ListApprovisionnement) =>
          new Approvisionnement(ListApprovisionnement));
      }).catch(this.handleError); // Attach an error handler to log potential errors to the console:
  }
  public postReception(value, approvisionnement:Approvisionnement) {
    approvisionnement.dateEffectue = value;
    return this.http
      .get(this.BaseURLAppro)
      .map(response => {
        const ListApprovisionnement = response.json();
        return ListApprovisionnement.map((ListApprovisionnement) =>
          new Approvisionnement(ListApprovisionnement));
      }).catch(this.handleError); // Attach an error handler to log potential errors to the console:
  }
  public addApprovisionnement(data) {
    return new Promise((resolve, reject) => {
      this.http
        .post(this.BaseURLAppro, data)
        .map(res => res.json())
        // This catch is very powerfull, it can catch all errors
        .catch((err: Response) => {
          reject((err || "Can't join the server."));
          // This return is required to compile but unuseable in your app
          return Observable.throw(err);
        })
        // The (err) => {} param on subscribe can't catch server down error so I keep only the catch
        .subscribe(data => { resolve(data) })
    })

  }
  private  handleError(error: Response) {
    return Observable.throw(error.statusText);
  }

}
