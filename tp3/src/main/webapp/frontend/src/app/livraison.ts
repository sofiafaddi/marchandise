export class Livraison {
  id: number;
  datePrevue: Date;
  magasin: string;
  quantite: number;
  dateEffectuee: Date;
  quantiteEffective: number;
  refMarchandise: number;
  nomEntrepot: string;
  dateCreation: number;
  effectuee: boolean;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
