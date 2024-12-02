using System.ComponentModel.DataAnnotations;

namespace API_ANDROID.Models;

public class Vartotojai
{
    [Key]
    public int Id { get; set; }
    public string Mac { get; set; }
    public string Sensorius { get; set; }
    public int Stiprumas { get; set; }
    
}