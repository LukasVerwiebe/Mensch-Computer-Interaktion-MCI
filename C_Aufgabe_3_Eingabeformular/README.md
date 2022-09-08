# Praktische Aufgabe Nr. 3: Eingabeformular

Erstellen Sie ein Fenster „Wein aufnehmen“, das später in der Gesamtanwendung über das Menü „Wein -> Aufnehmen“ aufgerufen wird. Das Fenster soll über zwei Reiter (in einer
TabPane) verfügen. Der erste Reiter soll das Formular für die Weindaten enthalten, der zweite Reiter das Trinkreifediagramm. Das Formular für die Weindaten soll angemessene
Interaktionselemente für die folgenden Daten enthalten:

• Bestellnummer (im Format 2021-BBBB-ZZZ, wobei BBBB vom Benutzer einzugebende Großbuchstaben ohne Sonderzeichen und ZZZ vom Benutzer einzugebende Ziffern sein sollen,

• Name des Weins,

• Jahrgang,

• Farbe (weiß, rot, rosé),

• Anbaugebiet, bestehend aus Land (eines aus etwa 20 Ländern) und Region (eine von etwa 20 Regionen pro Land, optional),

• Alkoholgehalt (7,5 - 25 Vol% in 0,5er-Schritten) oder Angabe „alkoholfrei“,

• Beschreibung des Weins als Text (optional),

• Flaschengröße,

• Preis pro Flasche brutto und netto,

• Literpreis brutto und netto,

• Lagerdauer.


Integrieren Sie die Funktionalität Ihres Preisumrechners in geeigneter Weise in das Formular.

Legen Sie – soweit sinnvoll - Vorbelegungen für die Interaktionselemente fest.

Überlegen Sie, welche lexikalischen, syntaktischen, semantischen und pragmatischen Prüfungen bei den einzelnen Interaktionselementen sinnvoll sind und zu welchem Zeitpunkt diese durchgeführt werden sollten. Realisieren Sie diese Prüfungen auf geeignete Weise. Falls nötig, sind aussagekräftige Fehlermeldungen vorzusehen.

Ergänzen Sie das Formular um zwei Schaltflächen: „Speichern“ und „Abbrechen“. „Speichern“ überprüft das Formular auf Vollständigkeit. Falls Angaben fehlen, soll eine
Fehlermeldung erzeugt werden und der Fokus auf das entsprechende Interaktionselement gesetzt werden. Wenn alle nötigen Angaben vorhanden und ohne ersichtlichen Fehler sind, wird daraus ein Objekt einer geeigneten Wein-Klasse erzeugt und zur Kontrolle auf System.out ausgegeben. Außerdem werden alle Interaktionselemente auf ihre Vorbelegung zurückgesetzt.

Beim Betätigen von „Abbrechen“ soll das Fenster geschlossen werden, falls noch keine Eingaben getätigt wurden. Wurden Eingaben vorgenommen, soll eine Nachfrage erscheinen, ob die Eingaben verworfen werden sollen.

Alle Interaktionselemente sollen in der üblichen Art mit Maus und Tastatur bedient werden können.

Denken Sie mit! Es reicht nicht aus, die Aufgabenstellung Buchstabe für Buchstabe zu lesen, ohne Ihren gesunden Menschenverstand zu verwenden. Was will der Nutzer? Stellen Sie sich vor, er muss tagtäglich hunderte von Weinen in das System einpflegen. Was kann ihm die Aufgabe wirklich erleichtern? Vorbelegungen können eine große Hilfe sein. Doch seien Sie kritisch, nicht überall ist eine Vorbelegung sinnvoll.
