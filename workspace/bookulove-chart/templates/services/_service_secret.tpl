{{- define "service.secret.head" }}
{{- $root := index . 0 }}
{{- $service := index $root.Values (index . 1) }}
apiVersion: v1
kind: Secret
metadata:
  name: {{ include "service.release.name" (list $root $service.metadata.name) }}
  labels:
    {{ include "service.labels" (list $root $service.metadata.name) | indent 4 }}
{{- end }}

{{- define "service.secret.ref" }}
{{- $root := index . 0 }}
{{- $service := index $root.Values (index . 1) }}
- name: {{ index . 2 }}
  valueFrom:
    secretKeyRef:
      name: {{ include "service.release.name" (list $root $service.metadata.name) }}
      key: {{ index . 3 }}
{{- end }}
