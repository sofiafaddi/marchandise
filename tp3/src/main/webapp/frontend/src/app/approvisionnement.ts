export class Approvisionnement {
  id: number;
  fournisseur: string ;
  quantite: number;
  refMarchandise: number;
  nomEntrepot: string;
  datePrevu: Date;
  dateEffectue: Date;
  dateCreation: number;
  quantiteEffective: number;
  effectuee: boolean;


  constructor(values: Object = {}) {
    Object.assign(this, values);


  }
}
