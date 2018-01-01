import { TestBed, inject } from '@angular/core/testing';

import { ApprovisionnementserviceService } from './approvisionnementservice.service';

describe('ApprovisionnementserviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ApprovisionnementserviceService]
    });
  });

  it('should be created', inject([ApprovisionnementserviceService], (service: ApprovisionnementserviceService) => {
    expect(service).toBeTruthy();
  }));
});
