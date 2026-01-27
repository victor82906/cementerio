import { TestBed } from '@angular/core/testing';

import { AyuntamientoService } from './ayuntamiento-service';

describe('AyuntamientoService', () => {
  let service: AyuntamientoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AyuntamientoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
