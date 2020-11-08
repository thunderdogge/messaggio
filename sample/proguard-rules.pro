-dontobfuscate
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

# Toothpick
-keep @android.support.annotation.Keep class *
-keep @javax.inject.Singleton class *
-dontwarn javax.inject.**
-dontwarn javax.annotation.**
-keep class **$$Factory { *; }
-keep class **$$MemberInjector { *; }
-adaptclassstrings
-keep class toothpick.** { *; }