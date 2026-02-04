import { TestBed } from '@angular/core/testing';

import { ConcesionService } from './concesion-service';

describe('ConcesionService', () => {
  let service: ConcesionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConcesionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
