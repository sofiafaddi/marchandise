import { Injectable } from '@angular/core';
import {Http, Response} from "@angular/http";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
// My import
import { Marchandisesmodele } from "./marchandisesmodele";


@Injectable()
export class MarchandisesService {
  BaseURLMarchandise = "http://localhost:8080/marchandise/";


  constructor(private http: Http){ }

  addMarchandise(data) {
    return new Promise((resolve, reject) => {
      this.http
        .post(this.BaseURLMarchandise, data)
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

  updateMarchandise(data, id) {
    return new Promise((resolve, reject) => {
      this.http
        .put(this.BaseURLMarchandise+"/"+id, data )
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

  getMarchandise(): Observable<Marchandisesmodele[]>{
    return this.http.get(this.BaseURLMarchandise). // This returns an Observable.
    map(response => {
      const ListMarchandise = response.json();
      return ListMarchandise.map((ListMarchandise) => new Marchandisesmodele(ListMarchandise));
    }).catch(this.handleError); // Attach an error handler to log potential errors to the console:
  }
  getMarchandiseByID(id:number): Observable<Marchandisesmodele> {
    return this.http.get(this.BaseURLMarchandise + "/" + id).map((response: Response) => {
      return new Marchandisesmodele(response.json());
    }).catch(this.handleError);
  }


 /* getMarchandise(): Observable<Marchandisesmodele[]>{
    return this.http.get(this.BaseURLMarchandise). // This returns an Observable.
    map((response: Response) => {
      return <Marchandisesmodele[]>response.json();
    }).catch(this.handleError); // Attach an error handler to log potential errors to the console:
  }*/
  /*getMarchandiseByID(id:number): Observable<Marchandisesmodele> {
    return this.http.get(this.BaseURLMarchandise+"/"+id).
    map((response: Response) => {
      return <Marchandisesmodele>response.json();
    })

  }*/
  DeleteMarchandise(id) {
    return new Promise((resolve, reject) => {
      this.http
        .delete(this.BaseURLMarchandise + "/" + id)
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
        .subscribe(data => {
          resolve(data)
        })
    })
  }
  /*private  handleError(error: Response) {
    return Observable.throw(error.statusText);
  }*/
  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    return Observable.throw(error);
  }

}
