import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubirFoto } from './subir-foto';

describe('SubirFoto', () => {
  let component: SubirFoto;
  let fixture: ComponentFixture<SubirFoto>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubirFoto]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubirFoto);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
