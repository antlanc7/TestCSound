<CsoundSynthesizer>
<CsOptions>
-odac
</CsOptions>
<CsInstruments>
sr = 44100

instr 1

kfreq chnget "slider"
a1 oscil 0dbfs/4, kfreq
out a1

endin

</CsInstruments>
<CsScore>
i1 0 36000
</CsScore>
</CsoundSynthesizer>

