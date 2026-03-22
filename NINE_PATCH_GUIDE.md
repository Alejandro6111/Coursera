# 📐 GUÍA COMPLETA: Crear Archivo Nine-Patch en Android

## ¿Qué es un Nine-Patch?

Un archivo nine-patch (`.9.png`) es una imagen PNG especial que define qué partes pueden estirarse y cuáles no. Esto permite que una imagen se redimensione sin distorsionarse.

### Ventajas
✅ La imagen se adapta a cualquier tamaño  
✅ Las partes importantes (como el marcianito) no se deforman  
✅ Perfecto para fondos de UI  

---

## 🎨 Método 1: Usar Draw 9-Patch Tool (RECOMENDADO)

### Paso 1: Preparar la imagen
1. Guarda tu imagen como `background.png` (sin el `.9`)
2. La imagen debe tener al menos 2 píxeles adicionales en cada borde para los marcadores
3. **Tamaño recomendado**: 400x600 píxeles

### Paso 2: Abrir Draw 9-Patch en Android Studio

**Windows/Linux:**
```
Tools → Android → Draw 9-Patch
```

**macOS:**
```
Android Studio → Tools → Android → Draw 9-Patch
```

### Paso 3: Cargar la imagen
1. Clic en `File → Open 9-patch...`
2. Selecciona tu `background.png`
3. Se abrirá la herramienta de edición

### Paso 4: Definir zonas de estiramiento

**En el panel izquierdo:**
- Verás tu imagen con bordes en gris (1 píxel de ancho)
- Ahora marcaremos qué partes pueden estirarse

#### 4.1 Marcar bordes de estiramiento (arriba y izquierda)
```
┌─────────────────────────────────┐
│ ██░░░░░░░░░░░░░░░░░░░░░░░░░███  │ ← Borde superior (marcadores negros)
│ ███                         ███  │
│ ███  [IMAGEN CON MASCOTA]   ███  │
│ ███  [DEL MARCIANITO]       ███  │
│ ███                         ███  │
│ ███░░░░░░░░░░░░░░░░░░░░░░░░░███  │
└─────────────────────────────────┘
  ↑
  Borde izquierdo (marcadores negros)
```

**Acciones:**
1. Haz clic en el borde superior (línea gris superior)
   - Arrastra donde quieras que se estire verticalmente
   - Dibuja líneas negras en las zonas que PUEDEN estirarse
   - Las zonas del marcianito deberían quedar sin marcar

2. Haz clic en el borde izquierdo (línea gris izquierda)
   - Dibuja líneas negras en las zonas que PUEDEN estirarse horizontalmente
   - El marcianito debe quedar en el centro sin marcar

#### 4.2 Marcar zona de contenido (derecha e inferior)
Los bordes derecho e inferior definen dónde puede ir el contenido (es automático en este caso, pero puedes ajustarlo):
- No necesitas marcar si quieres que sea todo el área interna

### Paso 5: Vista previa
En el panel derecho:
- **Preview 1x**: Muestra la imagen normal
- **Stretch**: Muestra cómo se vería estirada
- **Preview 4x**: Aumentado 4x para ver detalles

Asegúrate de que el marcianito no se distorsiona.

### Paso 6: Guardar
1. `File → Save 9-patch...`
2. Guarda como `background.9.png`
3. Colócalo en: `app/src/main/res/drawable/`

---

## 🖥️ Método 2: Herramienta en línea (alternativa rápida)

### Online Nine-Patch Generator
1. Visita: https://romannurik.github.io/AndroidAssetStudio/nine-patches.html
2. Carga tu imagen
3. Dibuja los marcadores
4. Descarga el `.9.png` resultante
5. Copia a `app/src/main/res/drawable/`

---

## 📝 Método 3: Crear manualmente (avanzado)

Un archivo `.9.png` tiene píxeles especiales en los bordes:

```
Estructura del Nine-Patch PNG:
┌────────────────────────────────────┐
│ █  BORDE NEGRO (1px) - Estiramiento│
│ █                                  │
│ █   Contenido original             │
│ █                                  │
│ █  BORDE TRANSPARENTE (1px)        │
└────────────────────────────────────┘
```

**Para crear manualmente:**
1. Abre Photoshop, GIMP o Paintbrush
2. Aumenta el lienzo +2px (arriba/abajo) y +2px (izquierda/derecha)
3. En los bordes nuevos, dibuja píxeles negros (#000000) donde deba estirarse
4. Exporta como PNG
5. Renombra a `background.9.png`

---

## ✅ Verificación Final

### Checklist:
- [x] Archivo es `background.9.png` (con `.9`)
- [x] Ubicación correcta: `app/src/main/res/drawable/`
- [x] Las áreas importantes NO están marcadas con negro
- [x] Tiene bordes totalmente transparentes (1px) o grises
- [x] Las zonas estirables están marcadas con píxeles negros (#000000)

### Uso en el código:
```xml
<!-- En activity_main.xml -->
<LinearLayout
    android:background="@drawable/background"
    .../>
```

**Nota**: En el XML, se usa `@drawable/background` sin el `.9`  
Android detecta automáticamente que es un nine-patch.

---

## 🐛 Solución de problemas

### La imagen se ve estirada o deformada
→ Verifica que los píxeles negros estén solo en las áreas que deben estirarse

### El marcianito se deforma
→ Asegúrate de NO marcar con negro las áreas donde está el marcianito

### El archivo no se reconoce
→ Confirma la extensión sea exactamente `.9.png`
→ Reinicia Android Studio: `File → Invalidate Caches...`

### Aparece con bordes negros en la app
→ Asegúrate de que los bordes (líneas de estiramiento) sean exactamente 1 píxel de ancho

---

## 📚 Ejemplo Visual

### Imagen original (400x300):
```
┌─────────────────────────────────┐
│                 [MASCOTA]        │
│                 ANDROID          │
│                                  │
└─────────────────────────────────┘
```

### Con nine-patch aplicado:
```
┌──────────────────────────────────┐
│██░░░░░░░░░░░░░░░░░░░░░░░░░░░░██  ← Bordes negros (stretch)
│██   [MASCOTA]          [+]    ██  ← Se estira aquí
│██   ANDROID                   ██  ← Mascota cuidada
│██░░░░░░░░░░░░░░░░░░░░░░░░░░░░██  ← Bordes negros
└──────────────────────────────────┘
```

Si la pantalla es más grande, se estira en las áreas marcadas, pero el marcianito se mantiene igual.

---

## 🔗 Referencias Útiles

- [Android Nine-Patch Docs](https://developer.android.com/guide/topics/graphics/2d-graphics#nine-patch)
- [Draw 9-Patch Documentation](https://developer.android.com/studio/write/draw9patch)
- [Asset Studio Online](https://romannurik.github.io/AndroidAssetStudio/nine-patches.html)

---

**¡Listo!** Tu fondo nine-patch está optimizado para todas las pantallas. 🎉
