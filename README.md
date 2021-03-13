# Java Refactoring project 2

Refactoring Beispiel einer Schauspielervermittlung, entsprechend Kapitel 1 aus
[_Refactoring 2nd Edition_](https://amzn.com/0134757599) von Martin Fowler.

Mit der neuen Auflage seines standardwerks zu Refactoring hat Martin Fowler das
Beispiel seines Buches an die moderne Welt angepasst:
Eine Theater verkauft mit seinen Schauspielern verschiedenen Veranstaltungen an denen 
Theaterstücke aufgeführt werden.
In der Regel kauft ein Kunde einige Vorführungen ein und das Theater berechnet dafür 
die Kosten auf Grundlage der Größe des Publikums und der Art des Stücks, das gespielt 
wird. Derzeit gibt es zwei Arten von Stücken, die das Unternehmen spielt: Tragödien und 
Komödien. 
Neben der Rechnung für eine Veranstaltung erhält ein Kunde auch eine Gutschrift, die für
Rabatte bei zukünftigen Veranstaltungen genutzt werden kann. Dies soll die Kundenbindung 
verbessern.
Die Speicherung der relevanten Daten erfolgt in JSON:

Plays.json
```json
{
  "hamlet": {"name": "Hamlet", "type": "tragedy"},
  "as-like": {"name": "As You Like It", "type": "comedy"},
  "othello": {"name": "Othello", "type": "tragedy"}
}
```

Invoices.json
```json
[
  {
    "customer": "BigCo",
    "performances": [
      {
        "playID": "hamlet",
        "audience": 55
      },
      {
        "playID": "as-like",
        "audience": 35
      },
      {
        "playID": "othello",
        "audience": 40
      }
    ]
  }
]
```
