export class EntrepotModele {
  nom: string;
  capacite: number ;
  occupation: number;
  marchandisesStockees: Array<string>;
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }

}
