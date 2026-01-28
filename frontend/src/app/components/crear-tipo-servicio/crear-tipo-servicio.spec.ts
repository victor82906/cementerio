import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearTipoServicio } from './crear-tipo-servicio';

describe('CrearTipoServicio', () => {
  let component: CrearTipoServicio;
  let fixture: ComponentFixture<CrearTipoServicio>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearTipoServicio]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearTipoServicio);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
