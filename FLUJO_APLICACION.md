# 🔄 FLUJO DE LA APLICACIÓN

## Diagrama de Inicio de la Aplicación

```
┌─────────────────────────────────────────────────────────┐
│ 1. Usuario toca icono de la app                         │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│ 2. Android carga AndroidManifest.xml                    │
│    - Busca MAIN intent filter                           │
│    - Encuentra MainActivity como actividad de inicio    │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│ 3. Crea instancia de MainActivity                       │
│    └─ Ejecuta MainActivity.onCreate()                   │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│ 4. View Binding infla el layout                         │
│    - Detecta orientación actual:                       │
│      ✓ Portrait → usa layout/activity_main.xml         │
│      ✓ Landscape → usa layout-land/activity_main.xml   │
│      ✓ Tablet → usa layout-sw600dp/activity_main.xml   │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│ 5. Carga recursos según el idioma del dispositivo       │
│    - Detecta locale (idioma):                          │
│      ✓ es (Spanish) → values-es/strings.xml           │
│      ✓ en (English) → values-en/strings.xml           │
│      ✓ fr (French) → values-fr/strings.xml            │
│      ✓ de (German) → values-de/strings.xml            │
│      ✓ Otro idioma → values/strings.xml (default)     │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│ 6. Renderiza la interfaz                                │
│    - Título: "¡Bienvenido!"                            │
│    - Mensaje descriptivo                               │
│    - Imagen (mascota Android)                          │
│    - Texto informativo                                 │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
         ┌───────────────────────┐
         │ 7. APP LISTA PARA      │
         │    INTERACTUAR CON     │
         │    EL USUARIO         │
         └───────────────────────┘
```

---

## 🎯 Proceso de Selección de Recursos

### Selección de Layout

```
Android detecta la pantalla actual y selecciona el layout:

┌─ ¿Tablet (600dp+)?
│  └─ SÍ → layout-sw600dp/activity_main.xml
│  └─ NO ↓
│
├─ ¿Landscape (horizontal)?
│  └─ SÍ → layout-land/activity_main.xml
│  └─ NO ↓
│
└─ DEFAULT → layout/activity_main.xml (portrait, teléfono)
```

**Ejemplo:**
- Teléfono portait: `layout/activity_main.xml`
- Teléfono landscape: `layout-land/activity_main.xml`
- Tablet: `layout-sw600dp/activity_main.xml`

### Selección de Strings (Textos)

```
Android detecta el idioma del dispositivo:

┌─ ¿El dispositivo está en Español?
│  └─ SÍ → values-es/strings.xml
│  └─ NO ↓
│
├─ ¿El dispositivo está en Inglés?
│  └─ SÍ → values-en/strings.xml
│  └─ NO ↓
│
├─ ¿El dispositivo está en Francés?
│  └─ SÍ → values-fr/strings.xml
│  └─ NO ↓
│
├─ ¿El dispositivo está en Alemán?
│  └─ SÍ → values-de/strings.xml
│  └─ NO ↓
│
└─ DEFAULT → values/strings.xml (Español)
```

**Ejemplo:**
- Español: `values-es/strings.xml`
- Inglés: `values-en/strings.xml`
- Otros idiomas: `values/strings.xml`

---

## 📱 Layouts por Tipo de Pantalla

### Portrait (Teléfono Vertical)

```
┌──────────────────────────┐
│                          │
│   ¡Bienvenido!          │
│   (welcome_title)        │
│                          │
│   Este es mi primer      │
│   proyecto Android...    │
│   (welcome_message)      │
│                          │
│   ┌────────────────┐     │
│   │   MARCIANITO   │     │
│   │    ANDROID     │     │
│   └────────────────┘     │
│   (android_cute)         │
│                          │
│   Español                │
│   (language_spanish)     │
│                          │
└──────────────────────────┘

Archivo: layout/activity_main.xml
Contenedor: LinearLayout vertical
Scroll: ScrollView para contenido largo
```

### Landscape (Teléfono Horizontal)

```
┌────────────────────────────────────┐
│                                    │
│  ¡Bienvenido!           ┌────────┐│
│  Este es mi primer      │MASCOTA││
│  proyecto Android...    │ANDROID││
│                         │        ││
│                         └────────┘│
│                                    │
└────────────────────────────────────┘

Archivo: layout-land/activity_main.xml
Contenedor: LinearLayout horizontal
Distribución: Dos columnas con weights iguales
```

### Tablet (600dp+)

```
┌────────────────────────────────────┐
│                                    │
│          ¡Bienvenido!              │
│                                    │
│   Este es mi primer proyecto       │
│   Android...                       │
│                                    │
│      ┌──────────────────┐          │
│      │   MASCOTA        │          │
│      │   ANDROID        │          │
│      │   (300x300)      │          │
│      └──────────────────┘          │
│                                    │
│            Español                 │
│                                    │
└────────────────────────────────────┘

Archivo: layout-sw600dp/activity_main.xml
Contenedor: LinearLayout centrado
Ancho: 500dp fijo
Espacios: Top y bottom para centrado vertical
```

---

## 🌍 Cambio de Idioma en Tiempo Real

### Cómo Android detecta el idioma:

```
Usuario:
  "Settings → System → Languages and input → Languages"
  └─ Cambia a: Français

┌──────────────────────────────────────┐
│ Android detecta el cambio            │
│ locale = fr_FR                       │
└──────────────┬───────────────────────┘
               │
               ▼
┌──────────────────────────────────────┐
│ Android destruye la actividad        │
│ MainActivity.onDestroy()             │
└──────────────┬───────────────────────┘
               │
               ▼
┌──────────────────────────────────────┐
│ Android recrea la actividad          │
│ MainActivity.onCreate()              │
│ → Se cargan strings-fr              │
└──────────────┬───────────────────────┘
               │
               ▼
┌──────────────────────────────────────┐
│ Interface se actualiza en Francés    │
│ "Bienvenue!" en lugar de "¡Welcome!"│
└──────────────────────────────────────┘
```

---

## 🎨 Sistema de Recursos

### Estructura Jerárquica

```
values/
├── strings.xml         ← Textos (Español default)
│   └─ app_name
│   └─ welcome_title
│   └─ welcome_message
│   └─ ...
│
├── colors.xml          ← Colores
│   └─ colorPrimary
│   └─ colorAccent
│   └─ ...
│
├── dimens.xml          ← Dimensiones (dp, sp)
│   └─ text_size_large (24sp)
│   └─ padding_large (24dp)
│   └─ image_size (200dp)
│   └─ ...
│
└── styles.xml          ← Estilos de vistas
    └─ TitleText (tamaño grande, negrita)
    └─ MessageText (tamaño medio)
    └─ ...

values-es/ (Español)
├── strings.xml         ← "¡Bienvenido!" (español)
├── (hereda colors.xml, dimens.xml, styles.xml)

values-en/ (Inglés)
├── strings.xml         ← "Welcome!" (inglés)
├── (hereda colors.xml, dimens.xml, styles.xml)

... y así para cada idioma
```

### Cómo recupera recursos MainActivity.kt

```kotlin
// Recuperar un String
val titulo = getString(R.string.welcome_title)
// Android automáticamente devuelve valores-es/strings.xml,
// values-en/strings.xml, etc., según el idioma

// Recuperar un color
val colorPrimario = getColor(R.color.colorPrimary)
// Se hereda de values/colors.xml

// Recuperar una dimensión
val imagenSize = resources.getDimensionPixelSize(R.dimen.image_size)
// Espacio en píxeles, convertido desde 200dp
```

---

## 🔄 Cambio de Orientación

```
Usuario: Rota el dispositivo

┌──────────────────────────────────┐
│ Android detecta rotación         │
│ Configuration ha cambiado        │
└────────────┬─────────────────────┘
             │
             ▼
┌──────────────────────────────────┐
│ ¿configChanges en Manifest?      │
│ android:configChanges=           │
│ "orientation|screenSize|..."     │
│ SÍ ✓                             │
└────────────┬─────────────────────┘
             │
             ▼
┌──────────────────────────────────┐
│ MainActivity.onConfigurationChanged()
│ Se ejecuta (NO hay onCreate)     │
│ Layout se ajusta correctamente   │
└──────────────────────────────────┘

Si NO tuviera configChanges:
└─ onCreate() se ejecutaría de nuevo
   (recrea la actividad)
```

---

## 📊 Tabla de Decisiones de Android

### Densidad de Pantalla

| Dispositivo | DPI | Carpeta | Escala |
|-------------|-----|---------|--------|
| Phone | 160 | drawable-mdpi | 1x |
| Phone | 240 | drawable-hdpi | 1.5x |
| Phone | 320 | drawable-xhdpi | 2x |
| Phone | 480 | drawable-xxhdpi | 3x |
| Tablet | 213 | drawable-tvdpi | 1.33x |

**En este proyecto**: Usamos Drawable XML (vectoriales), que se escalan automáticamente.

### Tamaño de Pantalla

| Tipo | Ancho | Carpeta |
|------|-------|---------|
| Teléfono pequeño | 320-479 dp | layout/ |
| Teléfono normal | 480-599 dp | layout/ |
| Tablet pequeña | 600-719 dp | layout-sw600dp/ |
| Tablet grande | 720+ dp | layout-sw720dp/ |

---

## 🎯 Ejemplo Completo de Ejecución

### Escenario: Samsung Galaxy S10 en Español, Portrait

```
1. Usuario toca el icono
   ↓
2. Android carga AndroidManifest.xml
   └─ Clase: com.example.miapp.MainActivity
   ↓
3. MainActivity.onCreate()
   ↓
4. binding = ActivityMainBinding.inflate(layoutInflater)
   └─ Android busca activity_main.xml
   └─ Detecta portrait, 360x640 dp
   └─ Carga: layout/activity_main.xml ✓
   ↓
5. setContentView(binding.root)
   ↓
6. setupUI()
   └─ binding.titleText.text = getString(R.string.welcome_title)
   └─ Android detecta idioma: es_ES
   └─ Carga: values-es/strings.xml
   └─ Devuelve: "¡Bienvenido!" ✓
   ↓
7. App muestra:
   ┌─────────────────┐
   │  ¡Bienvenido!   │
   │  Este es mi...  │
   │   [MASCOTA]     │
   │   Español       │
   └─────────────────┘
```

### Escenario: Usuario rota a Landscape

```
1. Usuario rota el dispositivo
   └─ De portrait (360x640) a landscape (640x360)
   ↓
2. Android detecta configChanges
   └─ Declarado en Manifest
   ↓
3. MainActivity.onConfigurationChanged()
   └─ Se ejecuta (NO onCreate)
   ↓
4. Android selecciona nuevo layout
   └─ Detecta landscape
   └─ Carga: layout-land/activity_main.xml
   └─ LinearLayout horizontal
   ↓
5. App muestra:
   ┌─────────────────────────────────┐
   │ ¡Bienvenido!      [MASCOTA]    │
   │ Este es mi...                  │
   └─────────────────────────────────┘
```

### Escenario: Usuario pone Tablet en Francés

```
1. Settings → Languages → Français
   ↓
2. Android recrea todas las actividades
   ↓
3. MainActivity.onCreate()
   └─ New locale: fr_FR
   ↓
4. Carga layout-sw600dp/activity_main.xml
   └─ Tablet con ancho ≥ 600dp
   ↓
5. getString() busca en values-fr
   └─ Devuelve: "Bienvenue!"
   ↓
6. App muestra:
   ┌───────────────────────────────┐
   │                               │
   │      Bienvenue!               │
   │ Ceci est mon premier projet... │
   │                               │
   │    ┌─────────────────┐        │
   │    │    MASCOTA      │        │
   │    │    ANDROID      │        │
   │    │    (300x300)    │        │
   │    └─────────────────┘        │
   │                               │
   └───────────────────────────────┘
```

---

## 🔍 Debugging: Ver qué se está Usando

En `MainActivity.kt` agregamos:

```kotlin
private fun logScreenInfo() {
    val displayMetrics = resources.displayMetrics
    val widthDp = (displayMetrics.widthPixels / density).toInt()
    
    Log.d("MainActivity", "Screen width: ${widthDp}dp")
    Log.d("MainActivity", "Orientation: $orientation")
    Log.d("MainActivity", "Locale: ${resources.configuration.locale}")
    // Esto te dice exactamente qué layout está cargando
}
```

En **Logcat** verás:
```
MainActivity: Screen width: 360dp, Orientation: Portrait
MainActivity: Locale: es_ES
```

Esto confirma:
- ✅ Se cargó: `layout/activity_main.xml`
- ✅ Se cargó: `values-es/strings.xml`

---

## 🎓 Resumen

```
┌─────────────────────────────────────┐
│ FLUJO AUTOMÁTICO DE ANDROID         │
└─────────────────────────────────────┘

Pantalla               │ Layout usado
────────────────────────────────────
Portrait, teléfono    │ layout/
Landscape, teléfono   │ layout-land/
Tablet (600dp+)       │ layout-sw600dp/

Idioma                │ Strings usado
────────────────────────────────────
Español               │ values-es/
Inglés                │ values-en/
Francés               │ values-fr/
Alemán                │ values-de/
Otros                 │ values/ (default)

TODO SUCEDE AUTOMÁTICAMENTE SIN
NECESIDAD DE CÓDIGO ESPECIAL ✓
```

---

**¡Así funciona tu aplicación Android!** 🎉
