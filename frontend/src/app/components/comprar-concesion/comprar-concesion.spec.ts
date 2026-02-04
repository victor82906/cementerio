import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComprarConcesion } from './comprar-concesion';

describe('ComprarConcesion', () => {
  let component: ComprarConcesion;
  let fixture: ComponentFixture<ComprarConcesion>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComprarConcesion]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComprarConcesion);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
