# Praktische Aufgabe Nr. 1: Trinkreifediagramm

Erzeugen Sie in einem Fenster ein Balkendiagramm für die Anzeige der Trinkreife eines Weines. Benutzen Sie hierfür nur Elemente der Klasse Rectangle und für die Beschriftung Elemente der Klasse Label, sowie die Container HBox, VBox, StackPane und Pane. Die Arbeitsfläche des Fensters soll 650 x 150 px einnehmen. Das Diagramm umfasst die fünf Jahre vor dem aktuellen Jahr, das aktuelle Jahr und die fünf Jahre nach dem aktuellen Jahr. Rechts und links von dem Balken wird ein Rand gelassen, der jeweils der Breite eines Jahres entspricht. Es soll also wie folgt aussehen:

![image](https://user-images.githubusercontent.com/63674539/189172374-331e1ce0-fdd6-4403-81e3-d0d0fe86070d.png)

Parameter für die Erstellung des Diagramms sind der Jahrgang des Weins (Jahr der Weinlese) und die mögliche Lagerdauer sowie das aktuelle Jahr. Das Diagramm umfasst das Jahr der Weinlese, daran anschließend die Jahre der Lagerdauer und außerdem ein weiteres Jahr zur Anzeige, dass der Wein nicht mehr genießbar ist. Insgesamt werden also 1+Lagerdauer+1 Jahre angezeigt. Das obige Bild zeigt das Diagramm für den Jahrgang 2018 bei 4 Jahren Lagerdauer (2019, 2020, 2021 und 2022), der 2023 nicht mehr trinkbar ist.

Die verschiedenen Stadien der Trinkreife berechnen sich in Bezug auf die Gesamtlänge aus Jahr der Weinlese plus Lagerdauer wie folgt:

• Zu früh zum Trinken ist der Wein im ersten Achtel der Spanne.

• In der Zeit vor dem optimalen Trinkgenuss ist der Wein geschmacklich noch steigerungsfähig.

• Der Bereich für den optimalen Trinkgenuss beträgt die Hälfte der Spanne.

• Im letzten Sechszehntel der Spanne lässt der Wein stark nach.

Nach Ablauf der Lagerdauer ist der Wein nicht mehr genießbar. Zur Visualisierung dieses Zustandes dient ein weiteres Jahr am Ende.

Der Jahrgang des Weines sowie das Jahr, in dem der Wein überlagert ist, werden beschriftet. Ist das Jahr der Überlagerung größer als das letzte im Balken darstellbare Jahr, wird diese Jahreszahl hinter das letzte darstellbare Jahr gesetzt. Liegt der Jahrgang vor dem ersten im Balken darstellbaren Jahr, wird die Jahreszahl vor das erste darstellbare Jahr gesetzt.

Das aktuelle Jahr wird als roter Rahmen dargestellt und in Rot beschriftet. Der Bereich, in dem es zu früh zum Trinken des Weines ist, soll grau (#c0c0c0, SILVER) dargestellt werden, der optimale Bereich grün (#00ff00, LIME), der Bereich dazwischen mit Farbverlauf von Grau nach Grün. Der überlagerte Bereich soll in Gelb (#ffff00, YELLOW) angezeigt werden, der Niedergang zwischen optimalem und überlagertem Bereich durch Farbverlauf von Grün nach Gelb.
