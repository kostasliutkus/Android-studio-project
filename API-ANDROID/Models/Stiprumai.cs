using System.ComponentModel.DataAnnotations;

namespace API_ANDROID.Models;

public class Stiprumai
{
    [Key]
    public int Id { get; set; }
    public int Matavimas { get; set; }
    public string Sensorius { get; set; }
    public int Stiprumas { get; set; }
    
}