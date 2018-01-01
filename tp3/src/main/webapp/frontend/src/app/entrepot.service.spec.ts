import { TestBed, inject } from '@angular/core/testing';

import { EntrepotService } from './entrepot.service';

describe('EntrepotService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EntrepotService]
    });
  });

  it('should be created', inject([EntrepotService], (service: EntrepotService) => {
    expect(service).toBeTruthy();
  }));
});
