{{- define "chart.labels" }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
