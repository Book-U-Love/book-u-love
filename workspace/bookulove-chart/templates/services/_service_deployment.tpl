{{- define "service.deployment.head" }}
{{- $root := index . 0 }}
{{- $service := index $root.Values (index . 1) }}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ include "service.release.name" (list $root $service.metadata.name) }}
  namespace: "{{ $root.Release.Namespace }}"
  labels: &labels
    {{- include "service.labels" (list $root $service.metadata.name) | indent 4 }}
{{- end }}
