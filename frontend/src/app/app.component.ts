import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {FilledSurvey} from "./FilledSurvey";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  id: number;
  filledSurvey = new FilledSurvey();

  constructor(private http: HttpClient,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(id => {
      if (id.id !== undefined) {
        this.id = id.id;
        this.http.get<FilledSurvey>('http://localhost:8080/user/surveys/try/' + this.id).subscribe(filledSurvey => {
          this.filledSurvey = filledSurvey;
        });
      }
    });
  }

  comebackToSpring() {
    this.http.post<any>('http://localhost:8080/user/surveys/finish-from-angular', this.filledSurvey).subscribe( hashFromBackend => {
      window.location.href = "http://localhost:8080/user/surveys/getHash/" + hashFromBackend.hash;
    }, error => {
      console.log(error)
    });
  }

  setTrueOnProperRadio(j: number, i: number, size: number) {
    for (var l = 0; l < size; l++) {
      this.filledSurvey.filledQuestions[j].filledAnswers[l].check = false;
    }
    this.filledSurvey.filledQuestions[j].filledAnswers[i].check = true;
  }
}

export interface Id {
  id: number;
}




