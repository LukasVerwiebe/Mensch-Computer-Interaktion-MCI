# Praktische Aufgabe Nr. 2: Preisumrechner

Erzeugen Sie eine FXML-Anwendung als Anwendungsfenster, das mit der Überschrift Preisumrechner bezeichnet ist. Das Fenster soll ein Eingabeformular enthalten, das eine
Klappliste für die Auswahl gängiger Weinflaschengrößen enthält. Die Flaschengrößen sind: 0,187 l, 0,25 l, 0,375 l, 0,5 l, 0,62 l, 0,7 l, 0,75 l, 0,8 l, 1 l, 1,5 l. Das Formular enthält vier Felder: Flaschenpreis netto und Flaschenpreis brutto in einer Zeile sowie Literpreis netto und Literpreis brutto in einer weiteren Zeile. Die Bruttopreise sind nicht editierbar. Zwischen den beiden Zeilen sind zwei Schaltflächen angeordnet, die mit  bzw.  bezeichnet sind. Führungstexte und Bezeichnungen der Einheiten werden wie auf dem Bild angeordnet. Die Anordnung der Elemente soll bei Veränderung der Fenstergröße erhalten bleiben, das Formular verändert sich nicht.

![image](https://user-images.githubusercontent.com/63674539/189173139-886269f7-284d-49af-bfa0-a74c6aebb73f.png)

Entwickeln Sie die Anwendung schrittweise zur vollständigen Funktionalität. In den Vorlesungen vom 08.11.2021 erlernen Sie die Techniken für den ersten Schritt, erst nach dem 15.11.2021 können Sie im zweiten Schritt die noch fehlenden Funktionen einbauen.

Schritt 1:

a) Im ersten Schritt dient das Feld für den Nettoliterpreis nur zur Ausgabe und ist daher nicht editierbar. Die Pfeil nach oben-Schaltfläche ist in diesem Stadium der Bearbeitung deaktiviert.
b) Wählen Sie eine sinnvolle Vorbelegung für die Flaschengröße. Einfach den ersten Wert in der Liste anzeigen zu lassen ist nicht ergonomisch.
c) Erstellen Sie für das Formular ein Programm, das beim Betätigen der Pfeil nach unten-Schaltfläche aus dem als Nettoflaschenpreis eingegebenen Wert je nach ausgewählter Flaschengröße den Nettoliterpreis errechnet und im Feld anzeigt. Benutzen Sie hierfür zunächst die amerikanische Formatierung für Dezimalzahlen.
d) Abhängig vom gesetzlichen Mehrwertsteuersatz sollen nach der Berechnung auch der Bruttoflaschenpreis und der Bruttoliterpreis ausgegeben werden.
e) Fangen Sie Fehleingaben im Nettoflaschenpreisfeld nach Betätigen der Pfeil nach unte -Schaltfläche ab und geben Sie passende Fehlermeldungen zunächst auf der Konsole aus.
f) Alle Interaktionselemente sollen in der üblichen Art mit Maus und Tastatur bedient werden können. Testen Sie auch, ob Ihr Programm auch ausschließlich mittels Tastatur bedient werden kann.
g) Berücksichtigen Sie ergonomische Anforderungen. Denken Sie an die Benutzer

Schritt 2:

a) Ersetzen Sie die Fehlermeldungen, die auf der Konsole ausgegeben werden durch Meldungsdialoge (Alert).
b) Erlauben Sie Eingaben im Feld für den Nettoliterpreis und die Umrechnung in der Gegenrichtung.
c) In allen Feldern sollen Dezimalzahlen im deutschen Format mit zwei Nachkommastellen benutzt werden.

Achten Sie darauf, dass Ihr Code verständlich und robust ist. Unter keinen Umständen darf in der Konsole eine Exception erscheinen. Daten wie die Flaschengrößen dürfen nur an einer einzigen Stelle gespeichert werden, an der sie leicht zu ändern sind (also nicht einerseits als die in der Klappliste angezeigten Texte und andererseits als die numerischen Werte).
