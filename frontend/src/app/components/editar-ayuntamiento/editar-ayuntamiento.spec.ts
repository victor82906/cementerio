import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarAyuntamiento } from './editar-ayuntamiento';

describe('EditarAyuntamiento', () => {
  let component: EditarAyuntamiento;
  let fixture: ComponentFixture<EditarAyuntamiento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarAyuntamiento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarAyuntamiento);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
