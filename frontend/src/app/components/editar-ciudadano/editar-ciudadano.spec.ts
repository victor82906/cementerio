import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarCiudadano } from './editar-ciudadano';

describe('EditarCiudadano', () => {
  let component: EditarCiudadano;
  let fixture: ComponentFixture<EditarCiudadano>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarCiudadano]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarCiudadano);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
