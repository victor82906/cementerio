import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ElementRef, ViewChild } from '@angular/core';
import { ChangeDetectorRef } from '@angular/core';


@Component({
  selector: 'app-audio-home',
  imports: [CommonModule],
  templateUrl: './audio-home.html',
  styleUrl: './audio-home.css',
})
export class AudioHome {
  
  reproduciendo: boolean = false;
  @ViewChild('audioPlayer') audioPlayerRef!: ElementRef;

  constructor(private cdr: ChangeDetectorRef){}

  toggleMusic() {
    const audio = this.audioPlayerRef.nativeElement;
    
    if (this.reproduciendo) {
      audio.pause();
      this.reproduciendo = false;
      this.cdr.markForCheck();
    } else {
      audio.play().then(() => {
        this.reproduciendo = true;
        this.cdr.markForCheck();
      }).catch((err: any) => {
        console.error("El navegador bloqueó la reproducción automática. El usuario debe interactuar primero.", err);
      });
    }
  }

}
