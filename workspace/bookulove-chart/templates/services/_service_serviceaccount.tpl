{{- define "service.serviceaccount" }}
{{- $root := index . 0 }}
{{- $service := index $root.Values (index . 1) }}
apiVersion: v1
kind: ServiceAccount
metadata:
  name: {{ include "service.release.name" (list $root $service.metadata.name) }}
  labels:
    {{- include "service.labels" (list $root $service.metadata.name) | indent 4 }}
secrets:
- name: {{ include "service.release.name" (list $root $service.metadata.name) }}
{{- end }}
