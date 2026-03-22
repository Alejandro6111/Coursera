# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-keepattributes *Annotation*,EnclosingClass,EnclosingMethod,InnerClasses,LineNumberTable,LocalVariableTable,LocalVariableTypeTable, Signature,SourceFile,Synthetic

# For support library
-keep class android.support.** { *; }
-keep interface android.support.** { *; }

# For Kotlin
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }

-keepclassmembers class * {
    public <init>(...);
}
