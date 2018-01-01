import { TestBed, inject } from '@angular/core/testing';

import { MarchandisesService } from './marchandises.service';

describe('MarchandisesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MarchandisesService]
    });
  });

  it('should be created', inject([MarchandisesService], (service: MarchandisesService) => {
    expect(service).toBeTruthy();
  }));
});
