export class Marchandisesmodele {
  reference: number;
  nom: string ;
  description: string;
  volumeUnitaire: number;
  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
