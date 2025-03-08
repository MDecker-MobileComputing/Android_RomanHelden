/**
 * Paket mit Screen für Einstellungen nach
 * https://developer.android.com/develop/ui/views/components/settings?hl=de#java
 *
 * <br><br>
 *
 * In Datei <i>app/build.gradle</i> muss im Block "dependencies" folgender
 * Eintrag hinzugefügt werden:
 * <pre>
 *     implementation libs.preference.ktx
 * </pre>
 *
 * Die gespeicherten Preference-Werte einer Installation der App können im
 * Emulator unter dem folgenden Pfad als XML-Datei mit dem "Device Explorer"
 * von Android Studio heruntergeladen werden:
 *
 * <pre>
 *     /data/data/de.eldecker.droid.romandhelden/shared_prefs
 * </pre>
 */
package de.eldecker.droid.romanhelden.einstellungen;