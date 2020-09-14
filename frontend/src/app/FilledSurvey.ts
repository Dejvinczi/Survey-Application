
export class FilledSurvey {
  surveyId?: number;
  hash?: string;
  surveyName: string;
  filledQuestions: Array<FilledQuestion>;
}

export class FilledQuestion {
  questionId?: number;
  question: string;
  filledAnswers: Array<FilledAnswer>;
}

export class FilledAnswer {
  answerId?: number;
  answer: string;
  check: boolean;
}
