import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CementerioBusqueda } from './cementerio-busqueda';

describe('CementerioBusqueda', () => {
  let component: CementerioBusqueda;
  let fixture: ComponentFixture<CementerioBusqueda>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CementerioBusqueda]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CementerioBusqueda);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
