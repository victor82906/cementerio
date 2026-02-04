import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComprarServicio } from './comprar-servicio';

describe('ComprarServicio', () => {
  let component: ComprarServicio;
  let fixture: ComponentFixture<ComprarServicio>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComprarServicio]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComprarServicio);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
