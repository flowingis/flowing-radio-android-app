package it.flowing.flowingradio.models

class Episode (
    val episode_id: Double,
    val type: String,
    val title: String,
    val duration: Double,
    val explicit: Boolean,
    val show_id: Double,
    val author_id: Double,
    val image_url: String,
    val image_original_url: String,
    val published_at: String,
    val download_enabled: Boolean,
    val waveform_url: String,
    val site_url: String,
    val download_url: String,
    val playback_url: String
)