import { TestBed, inject } from '@angular/core/testing';

import { LivraisonserviceService } from './livraisonservice.service';

describe('LivraisonserviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LivraisonserviceService]
    });
  });

  it('should be created', inject([LivraisonserviceService], (service: LivraisonserviceService) => {
    expect(service).toBeTruthy();
  }));
});
