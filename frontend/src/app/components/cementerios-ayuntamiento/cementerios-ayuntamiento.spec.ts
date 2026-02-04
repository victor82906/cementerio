import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CementeriosAyuntamiento } from './cementerios-ayuntamiento';

describe('CementeriosAyuntamiento', () => {
  let component: CementeriosAyuntamiento;
  let fixture: ComponentFixture<CementeriosAyuntamiento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CementeriosAyuntamiento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CementeriosAyuntamiento);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
