import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroAyuntamiento } from './registro-ayuntamiento';

describe('RegistroAyuntamiento', () => {
  let component: RegistroAyuntamiento;
  let fixture: ComponentFixture<RegistroAyuntamiento>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistroAyuntamiento]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistroAyuntamiento);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
