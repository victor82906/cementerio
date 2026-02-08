import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AudioHome } from './audio-home';

describe('AudioHome', () => {
  let component: AudioHome;
  let fixture: ComponentFixture<AudioHome>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AudioHome]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AudioHome);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
