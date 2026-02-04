import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroCementerio } from './registro-cementerio';

describe('RegistroCementerio', () => {
  let component: RegistroCementerio;
  let fixture: ComponentFixture<RegistroCementerio>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistroCementerio]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistroCementerio);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
