import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiciosAyuntamiento } from './servicios-ayuntamiento';

describe('ServiciosAyuntamiento', () => {
  let component: ServiciosAyuntamiento;
  let fixture: ComponentFixture<ServiciosAyuntamiento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServiciosAyuntamiento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiciosAyuntamiento);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
