{{- define "service.service" }}
{{- $root := index . 0 }}
{{- $service := index $root.Values (index . 1) }}
apiVersion: v1
kind: Service
metadata:
  name: {{ include "service.release.name" (list $root $service.metadata.name) }}
  labels: &labels
    {{- include "service.labels" (list $root $service.metadata.name) | indent 4 }}
spec:
  selector: *labels
  {{- $type := "ClusterIP "}}
  {{- if gt (len .) 2 }}
    {{- $type = index . 2 }}
  {{- end }}
  type: {{ $type }}
  ports:
  - protocol: TCP
    port: {{ $service.server.port }}
    targetPort: {{ $service.server.port }}
{{- end }}
