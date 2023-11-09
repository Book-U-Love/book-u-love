{{- define "service.release.name" -}}
{{- $root := index . 0 -}}
{{- $service := index $root.Values (index . 1) -}}
{{ print $root.Release.Name "-" $service.metadata.org "-" $service.metadata.name }}
{{- end }}

{{- define "service.labels" }}
{{- $root := index . 0 }}
{{- $service := index $root.Values (index . 1) }}
{{- include "chart.labels" $root }}
app.kubernetes.io/name: {{ $service.metadata.name }}
app.kubernetes.io/version: {{ $service.metadata.version }}
{{- end }}

{{- define "service.image" -}}
{{ print .metadata.org "/" .metadata.name ":" .metadata.version }}
{{- end }}
